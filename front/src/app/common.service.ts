import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {SelectItem} from 'primeng/api';
import * as moment from 'moment';

@Injectable()
export class CommonService {

  constructor(private httpClient: HttpClient) { }

  public find(url: string, findParams: {}): Observable<any>  {
    return this.httpClient.get(url, {params: this.getHttpParams(findParams)});
  }

  public get(url: string): Observable<any> {
    return this.httpClient.get(url);
  }

  public getUserRoles(offset: number, limit: number): Observable<any> {
    return this.httpClient.get('/user-role', {params: this.getHttpParams({'offset': offset, 'limit': limit})});
  }

  public save(url: string, obj: any): Observable<any> {
    if (!obj.id) {
      return this.httpClient.post(url, obj);
    } else {
      return this.httpClient.put(url, obj);
    }
  }

  public delete(url: string): Observable<any> {
    return this.httpClient.delete(url);
  }

  public getUsersType(): SelectItem[] {
    return [
      {label: 'admin', value: 'admin'},
      {label: 'user', value: 'user'}
    ];
  }

  public parseDateFromString(dateStr: any): Date {
    return dateStr ? moment(dateStr).toDate() : null;
  }

  private getHttpParams(params: {}): HttpParams {
    let httpParams = new HttpParams();
    for (const param of Object.keys(params)) {
      httpParams = httpParams.set(param.toString(), params[param].toString());
    }
    return httpParams;
  }

}


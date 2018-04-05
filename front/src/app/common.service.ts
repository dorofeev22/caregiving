import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {SelectItem} from 'primeng/api';

@Injectable()
export class CommonService {

  constructor(private httpClient: HttpClient) { }

  public find(url: string, page: number, size: number, params: HttpParams): Observable<any> {
    return this.httpClient.get(url, {
      params: params.set('page', page.toString()).set('size', size.toString())
    });
  }

  public findUsers(page: number, size: number, findParams: {}): Observable<any>  {
    let httpFindParams = new HttpParams();
    for (const param of Object.keys(findParams)) {
      httpFindParams = httpFindParams.set(param.toString(), findParams[param].toString());
    }
    return this.find('/user', page, size, httpFindParams);
  }

  public get(url: string): Observable<any> {
    return this.httpClient.get(url);
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

}


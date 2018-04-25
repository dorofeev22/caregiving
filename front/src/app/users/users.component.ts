import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {CommonService} from '../common.service';
import {User} from '../domain/user';
import {LazyLoadEvent} from 'primeng/primeng';
import {SelectItem} from 'primeng/api';
import {Subject} from 'rxjs/Subject';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import {UserRole} from '../domain/userRole';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  url: string;
  users: User[];
  userTypes: SelectItem[];
  userRoles: UserRole[];
  userRolesCount: number;
  cols: any;
  totalRecords: number;
  loading: boolean;
  rows: number;
  modelChanged: Subject<User> = new Subject<User>();

  constructor(private commonService: CommonService, private router: Router) {
    this.userTypes = this.commonService.getUsersType();
    this.commonService.getUserRoles(0, 0).subscribe(data => {
      this.userRolesCount = data.totalElements;
    });
    this.modelChanged
      .debounceTime(300) // wait 300ms after the last event before emitting last event
      .subscribe(user => this.save(user));
  }

  ngOnInit() {
    this.rows = 5;
    this.url = '/user';
    this.loading = true;
    this.findUsers({'page': 0, 'size': this.rows});
    this.cols = [
      { field: 'name', header: 'User name' },
      { field: 'login', header: 'Login' },
      { field: 'type', header: 'Type' },
      { field: 'role', header: 'Role' }
    ];
  }

  loadUsers(event: LazyLoadEvent) {
    const findParams = {};
    if (event.filters) {
      for (const fieldName of Object.keys(event.filters)) {
        const fieldMetaData = event.filters[fieldName];
          if (fieldMetaData) {
            findParams[fieldName] = fieldMetaData.value;
        }
      }
    }
    findParams['page'] = event.first / event.rows;
    findParams['size'] = event.rows;
    this.findUsers(findParams);
  }

  findUsers(findParams: {}) {
    this.commonService.find(this.url, findParams).subscribe(
      data => {
        this.users = data.content;
        this.totalRecords = data.totalElements;
        this.loading = false;
      });
  }

  delete(id: number) {
    this.commonService.delete(this.url + '/' + id.toString()).subscribe(res => {
      this.ngOnInit();
      }, msg => {
        console.error(`Error: ${msg.status} ${msg.statusText}`);
      }
    );
  }

  changed(user: User) {
    this.modelChanged.next(user);
  }

  save(user: User) {
    this.commonService.save(this.url, user).subscribe(res => {
      // TODO accept response
    });
  }

}

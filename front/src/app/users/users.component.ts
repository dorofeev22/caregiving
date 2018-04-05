import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {CommonService} from '../common.service';
import {User} from '../domain/user';
import {LazyLoadEvent} from 'primeng/primeng';
import {SelectItem} from 'primeng/api';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[];
  userTypes: SelectItem[];
  cols: any;
  totalRecords: number;
  loading: boolean;
  rows: number;

  constructor(private commonService: CommonService, private router: Router) {
    this.userTypes = this.commonService.getUsersType();
  }

  ngOnInit() {
    this.rows = 5;
    this.loading = true;
    this.findUsers(0, this.rows, new Map<string, string>());
    this.cols = [
      { field: 'name', header: 'User name' },
      { field: 'login', header: 'Login' },
      { field: 'type', header: 'Type' }
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
    this.findUsers(event.first / event.rows, event.rows, findParams);
  }

  findUsers(page: number, size: number, findParams: {}) {
    this.commonService.findUsers(page, size, findParams).subscribe(
      data => {
        this.users = data.content;
        this.totalRecords = data.totalElements;
        this.loading = false;
      });
  }

  delete(id: number) {
    this.commonService.delete('/user/' + id.toString()).subscribe(res => {
      this.ngOnInit();
      }, msg => {
        console.error(`Error: ${msg.status} ${msg.statusText}`);
      }
    );
  }


}

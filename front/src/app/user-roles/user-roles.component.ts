import {ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CommonService} from '../common.service';
import {UserRole} from '../domain/userRole';
import {LazyLoadEvent} from 'primeng/primeng';

@Component({
  selector: 'app-user-roles',
  templateUrl: './user-roles.component.html',
  styleUrls: ['./user-roles.component.css']
})
export class UserRolesComponent implements OnInit {

  userRoles: UserRole[];
  cols: any;
  totalRecords: number;
  tableRowsCount: number;
  rowsCount: number;
  @Input() userRole: UserRole;
  @Output() userRoleChange  = new EventEmitter<UserRole>();

  constructor(private commonService: CommonService) {
  }

  ngOnInit() {
    this.rowsCount = 5; // event.rows == rowsCount * 2. Why?
    this.cols = [
      {field: 'id', header: 'Id'},
      {field: 'name', header: 'Role name'},
      {field: 'description', header: 'Role description'}
    ];
  }

  changeRole(role: UserRole) {
    this.userRoleChange.emit(role);
  }

  loadRoles(event: LazyLoadEvent) {
    this.commonService.getUserRoles(event.first, event.rows).subscribe(data => {
      this.userRoles = <UserRole[]> data.content;
      this.totalRecords = data.totalElements;
      this.tableRowsCount = this.totalRecords + this.rowsCount;
    });
  }

}

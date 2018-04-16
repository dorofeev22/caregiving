import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CommonService} from '../common.service';
import {UserRole} from '../domain/userRole';

@Component({
  selector: 'app-user-roles',
  templateUrl: './user-roles.component.html',
  styleUrls: ['./user-roles.component.css']
})
export class UserRolesComponent implements OnInit {

  @Input() userRoles: UserRole[];
  cols: any;
  @Input() userRole: UserRole;
  @Output() userRoleChange  = new EventEmitter<UserRole>();

  constructor(private commonService: CommonService) {
  }

  ngOnInit() {
    this.cols = [
      {field: 'id', header: 'Id'},
      {field: 'name', header: 'Role name'},
      {field: 'description', header: 'Role description'}
    ];
  }

  changeRole(role: UserRole) {
    this.userRoleChange.emit(role);
  }

}

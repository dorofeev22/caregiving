import {UserRole} from './userRole';

export class User {
  id;
  name;
  login;
  type;
  password;
  userRoleId;
  userRoleName;
  role: UserRole;
}

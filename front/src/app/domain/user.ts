import {UserRole} from './userRole';

export class User {
  id;
  name;
  login;
  type;
  password;
  role: UserRole;
  lockDateTime: Date;
}

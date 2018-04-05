import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {User} from '../domain/user';
import {CommonService} from '../common.service';
import {FormBuilder, FormControl, FormGroup, ValidatorFn, Validators} from '@angular/forms';
import {SelectItem} from 'primeng/api';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user: User;
  id: number;
  userForm: FormGroup;
  userTypes: SelectItem[];
  nameMaxLength: number;
  loginMaxLength: number;
  url = '/user';

  constructor(private commonService: CommonService, private router: Router,
              private activatedRoute: ActivatedRoute, private fb: FormBuilder) {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = params['id'];
    });
    this.nameMaxLength = 100;
    this.loginMaxLength = 50;
    this.userTypes = this.commonService.getUsersType();
  }

  ngOnInit() {
    this.userForm = this.fb.group({
      name: new FormControl('', Validators.compose([
        Validators.required, Validators.pattern('.*[^\\s].*'), Validators.maxLength(this.nameMaxLength)
      ])),
      login: new FormControl('', Validators.compose([
        Validators.required, Validators.maxLength(this.loginMaxLength)
      ])),
      type: new FormControl('', Validators.required),
      password: new FormControl(''),
      id: []
    });
    if (!this.id) {
      this.user = new User();
    } else {
      this.commonService.get(this.url + '/' + this.id.toString()).subscribe(
        data => {
          this.user = <User> data;
          this.userForm.setValue(this.user);
        });
    }
    this.togglePasswordValidators();
  }

  save() {
    this.user = this.userForm.value;
    this.commonService.save(this.url, this.user).subscribe(res => {
        this.router.navigate(['users']);
      });
  }

  private togglePasswordValidators() {
    const pswControl = this.userForm.get('password');
    const pswValidators: ValidatorFn[] = [Validators.required];
    if (!this.id) {
      pswControl.setValidators(pswValidators);
    } else {
      pswControl.clearValidators();
    }
    pswControl.updateValueAndValidity();
  }
}

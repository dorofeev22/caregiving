import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {DynamicFormComponent} from './dynamic-form/containers/dynamic-form/dynamic-form.component';
import {Validators} from '@angular/forms';
import {FieldConfig} from './dynamic-form/models/field-config.interface';
import {CommonService} from './common.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit, OnInit {
  @ViewChild(DynamicFormComponent) form: DynamicFormComponent;
  title = 'My Application';
  userTypes: string[];
  config: FieldConfig[] = [
    {
      type: 'input',
      label: 'Full name',
      name: 'name',
      placeholder: 'Enter your name',
      validation: [Validators.required, Validators.minLength(4)]
    },
    {
      type: 'select',
      label: 'Favourite Food',
      name: 'food',
      options: this.userTypes,
      placeholder: 'Select an option',
      validation: [Validators.required]
    },
    {
      label: 'Submit',
      name: 'submit',
      type: 'button'
    }
  ];

  constructor(private commonService: CommonService) {
  }

  ngOnInit () {
    this.getUserTypes();
  }

  ngAfterViewInit() {
    let previousValid = this.form.valid;
    this.form.changes.subscribe(() => {
      if (this.form.valid !== previousValid) {
        previousValid = this.form.valid;
        this.form.setDisabled('submit', !previousValid);
      }
    });
    this.form.setDisabled('submit', true);
    this.form.setValue('name', 'Todd Motto');
  }

  submit(value: {[name: string]: any}) {
    console.log(value);
  }

  getUserTypes() {
    this.commonService.get('/user/userTypes').subscribe(data => {
      this.userTypes = <string[]>data;
    });
    // return ['Pizza', 'Hot Dogs', 'Knakworstje', 'Coffee'];
  }

}

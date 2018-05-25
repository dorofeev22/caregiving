import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {DynamicFormComponent} from './dynamic-form/containers/dynamic-form/dynamic-form.component';
import {ValidatorFn, Validators} from '@angular/forms';
import {FieldConfig} from './dynamic-form/models/field-config.interface';
import {CommonService} from './common.service';
import {ValidationType} from './dynamic-form/models/validation-type';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit, OnInit {
  @ViewChild(DynamicFormComponent) form: DynamicFormComponent;
  title = 'My Application';
  config: FieldConfig[] = [];

  constructor(private commonService: CommonService) {
  }

  ngOnInit () {
    this.getFormInfo();
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
  }

  submit(value: {[name: string]: any}) {
    console.log(value);
  }

  getFormInfo() {
    this.commonService.get('/formInfo/1').subscribe(data => {
      const userTypes = <string[]>data;
      this.config = data.info.fileds;
      this.createValidators();
    });
  }

  createValidators() {
    for (const fieldConfig of this.config) {
      if (fieldConfig.validationRules) {
        const validators: ValidatorFn[] = [];
        for (const validationRule of fieldConfig.validationRules) {
          const type = validationRule.type, value = validationRule.value;
          if (type === ValidationType.mandatory) {
            validators.push(Validators.required);
          }
          if (type === ValidationType.regexp) {
            validators.push(Validators.pattern(value));
          }
          if (type === ValidationType.minLength) {
            validators.push(Validators.minLength(value));
          }
          if (type === ValidationType.maxLength) {
            validators.push(Validators.maxLength(value));
          }
          if (type === ValidationType.minVal) {
            validators.push(Validators.min(value));
          }
          if (type === ValidationType.maxVal) {
            validators.push(Validators.max(value));
          }
        }
        fieldConfig.validation = validators;
      }
    }
  }

}

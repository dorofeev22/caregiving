import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {DynamicFormComponent} from './dynamic-form/containers/dynamic-form/dynamic-form.component';
import {ValidatorFn, Validators} from '@angular/forms';
import {FieldConfig} from './dynamic-form/models/field-config.interface';
import {CommonService} from './common.service';
import {ValidationType} from './dynamic-form/models/validation-type';
import {ValidationRule} from './dynamic-form/models/validation-rule.interface';

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
    // TODO choose form identificator
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
        const validationMessages: any[] = [];
        for (const validationRule of fieldConfig.validationRules) {
          const type = validationRule.type, value = validationRule.value;
          if (type === ValidationType.mandatory) {
            validators.push(Validators.required);
            validationMessages.push(this.createValidationMessage('required', validationRule, 'required'));
          }
          if (type === ValidationType.regexp) {
            validators.push(Validators.pattern(value));
            validationMessages.push(this.createValidationMessage('pattern', validationRule, 'Unacceptable characters'));
          }
          if (type === ValidationType.minLength) {
            validators.push(Validators.minLength(value));
            validationMessages.push(this.createValidationMessage('minlength', validationRule, 'Minimum length ' + value));
          }
          if (type === ValidationType.maxLength) {
            validators.push(Validators.maxLength(value));
            validationMessages.push(this.createValidationMessage('maxlength', validationRule, 'Maximum length ' + value));
          }
          if (type === ValidationType.minVal) {
            validators.push(Validators.min(value));
            validationMessages.push(this.createValidationMessage('min', validationRule, 'Minimum value  ' + value));
          }
          if (type === ValidationType.maxVal) {
            validators.push(Validators.max(value));
            validationMessages.push(this.createValidationMessage('max', validationRule, 'Maximum value ' + value));
          }
        }
        fieldConfig.validation = validators;
        fieldConfig.validationMessages = validationMessages;
      }
    }
  }

  /**
   * Create object with validator name and validation message
   * @param {string} validatorName is name as in form.js
   * @param {ValidationRule} validationRule is rule with error message
   * @param {string} defaultMessage is message if rule message is not exists
   */
  private createValidationMessage(validatorName: string, validationRule: ValidationRule, defaultMessage: string): any {
    return {'validatorName': validatorName, 'message': (validationRule.message ? validationRule.message : defaultMessage)};
  }

}

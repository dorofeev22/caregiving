import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {DynamicFormComponent} from '../dynamic-form/containers/dynamic-form/dynamic-form.component';
import {ValidatorFn, Validators} from '@angular/forms';
import {FieldConfig} from '../dynamic-form/models/field-config.interface';
import {CommonService} from '../common.service';
import {ValidationType} from '../dynamic-form/models/validation-type';
import {ValidationRule} from '../dynamic-form/models/validation-rule.interface';

@Component({
  selector: 'app-document',
  template: `
    <h3>Document</h3>
    <div>
      <app-dynamic-form [config]="config" #form="dynamicForm" (submit)="submit($event)"></app-dynamic-form>
    </div>
    <p-listbox [options]="cities2" [(ngModel)]="selectedCity2" optionLabel="name"></p-listbox>
    <div>{{selectedCity2 ? selectedCity2.name : ''}}</div>
  `,
  styleUrls: ['./document.component.css']
})
export class DocumentComponent implements AfterViewInit, OnInit {
  @ViewChild(DynamicFormComponent) form: DynamicFormComponent;
  config: FieldConfig[] = [];
  cities2: any[];
  selectedCity2: any;

  constructor(private commonService: CommonService) {
    this.cities2 = [
      {name: 'New York', code: 'NY'},
      {name: 'Rome', code: 'RM'},
      {name: 'London', code: 'LDN'},
      {name: 'Istanbul', code: 'IST'},
      {name: 'Paris', code: 'PRS'}
    ];
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
    // TODO choose form identifier
    this.commonService.get('/formInfo/1').subscribe(data => {
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
          const value = validationRule.value;
          switch (validationRule.type) {
            case ValidationType.mandatory:
              validators.push(Validators.required);
              validationMessages.push(this.createValidationMessage('required', validationRule, 'required'));
              break;
            case ValidationType.regexp:
              validators.push(Validators.pattern(value));
              validationMessages.push(this.createValidationMessage('pattern', validationRule, 'Unacceptable characters'));
              break;
            case ValidationType.minLength:
              validators.push(Validators.minLength(value));
              validationMessages.push(this.createValidationMessage('minlength', validationRule, 'Minimum length ' + value));
              break;
            case ValidationType.maxLength:
              validators.push(Validators.maxLength(value));
              validationMessages.push(this.createValidationMessage('maxlength', validationRule, 'Maximum length ' + value));
              break;
            case ValidationType.minVal:
              validators.push(Validators.min(value));
              validationMessages.push(this.createValidationMessage('min', validationRule, 'Minimum value  ' + value));
              break;
            case ValidationType.maxVal:
              validators.push(Validators.max(value));
              validationMessages.push(this.createValidationMessage('max', validationRule, 'Maximum value ' + value));
              break;
            default:
              console.log('Unknown validation rule type');
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

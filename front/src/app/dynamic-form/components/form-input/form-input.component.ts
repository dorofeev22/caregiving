import {Component} from '@angular/core';
import {FormGroup} from '@angular/forms';

import {Field} from '../../models/field.interface';
import {FieldConfig} from '../../models/field-config.interface';

@Component({
  selector: 'app-form-input',
  styleUrls: ['form-input.component.css'],
  template: `
    <div class="dynamic-field form-input" [formGroup]="group">
      <label>{{ config.label }}</label>
      <input *ngIf="config.valueType == 'string'" type="text" [attr.placeholder]="config.placeholder" [formControlName]="config.name" />
      <input *ngIf="config.valueType == 'number'" type="number" [attr.placeholder]="config.placeholder" [formControlName]="config.name" />
      <input *ngIf="config.valueType == 'boolean'" type="checkbox" [formControlName]="config.name" />
      <div *ngIf="!group.controls[config.name].valid && group.controls[config.name].dirty">
        <div *ngFor="let msg of config.validationMessages">
          <span *ngIf="group.controls[config.name].errors[msg.validatorName]">{{msg.message}}</span>
        </div>
      </div>
    </div>
  `
})
export class FormInputComponent implements Field {
  config: FieldConfig;
  group: FormGroup;
}

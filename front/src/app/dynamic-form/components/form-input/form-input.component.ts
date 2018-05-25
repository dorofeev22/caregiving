import { Component, ViewContainerRef } from '@angular/core';
import { FormGroup } from '@angular/forms';

import { Field } from '../../models/field.interface';
import { FieldConfig } from '../../models/field-config.interface';

@Component({
  selector: 'app-form-input',
  styleUrls: ['form-input.component.css'],
  template: `
    <div class="dynamic-field form-input" [formGroup]="group">
      <label>{{ config.label }}</label>
      <input
        type="text"
        [attr.placeholder]="config.placeholder"
        [formControlName]="config.name" />
      <div *ngIf="!group.controls[config.name].valid && group.controls[config.name].dirty">
        <span *ngIf="group.controls[config.name].errors['required']">Is required</span>
        <span *ngIf="group.controls[config.name].errors['pattern']">Unacceptable characters</span>
        <span *ngIf="group.controls[config.name].errors['minlength']">Min length</span>
      </div>
    </div>
  `
})
export class FormInputComponent implements Field {
  config: FieldConfig;
  group: FormGroup;
}

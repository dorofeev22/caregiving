import {Component} from '@angular/core';
import {FormGroup} from '@angular/forms';

import {Field} from '../../models/field.interface';
import {FieldConfig} from '../../models/field-config.interface';

@Component({
  selector: 'app-form-select',
  styleUrls: ['form-select.component.css'],
  template: `
    <div class="dynamic-field form-select" [formGroup]="group">
      <label>{{ config.label }}</label>
      <select [formControlName]="config.name">
        <option value="">{{ config.placeholder }}</option>
        <option *ngFor="let option of config.options">
          {{ option.value }}
        </option>
      </select>
      <p-listbox [formControlName]="config.name" [options]="config.options" [(ngModel)]="config.selectedOptions">
        <ng-template let-itm let-i="index" pTemplate="item">
          <div class="ui-helper-clearfix">
            <span>{{i}}</span>
            <span>{{itm.label}}</span>
            <span>{{itm.value}}</span>
          </div>
        </ng-template>
      </p-listbox>
      <div>{{config.selectedOptions ? config.selectedOptions.label : ''}}</div>
      <div>{{config.selectedOptions}}</div>
    </div>
  `,
})
export class FormSelectComponent implements Field {
  config: FieldConfig;
  group: FormGroup;
}

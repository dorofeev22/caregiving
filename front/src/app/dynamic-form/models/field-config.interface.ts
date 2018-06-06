import {ValidatorFn} from '@angular/forms';
import {ValidationRule} from './validation-rule.interface';
import {SelectItem} from 'primeng/api';

export interface FieldConfig {
  disabled?: boolean;
  label?: string;
  name: string;
  options?: SelectItem[];
  selectedOptions: SelectItem;
  placeholder?: string;
  type: string;
  validation?: ValidatorFn[];
  validationRules?: ValidationRule[];
  validationMessages?: any[];
  value?: any;
  valueType?: string;
}

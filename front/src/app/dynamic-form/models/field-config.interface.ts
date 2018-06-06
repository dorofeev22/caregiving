import {ValidatorFn} from '@angular/forms';
import {ValidationRule} from './validation-rule.interface';

export interface FieldConfig {
  disabled?: boolean;
  label?: string;
  name: string;
  options?: string[];
  placeholder?: string;
  type: string;
  validation?: ValidatorFn[];
  validationRules?: ValidationRule[];
  validationMessages?: any[];
  value?: any;
  valueType?: string;
}

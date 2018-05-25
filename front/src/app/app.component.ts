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
    this.form.setValue('name', 'Todd Motto');
  }

  submit(value: {[name: string]: any}) {
    console.log(value);
  }

  getFormInfo() {
    this.commonService.get('/formInfo/1').subscribe(data => {
      const userTypes = <string[]>data;
      this.config = data.info.fileds;
    });
  }

}

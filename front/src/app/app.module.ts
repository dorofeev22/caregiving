import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TableModule} from 'primeng/table';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';
import {PanelModule} from 'primeng/panel';
import {DropdownModule} from 'primeng/dropdown';
import {CalendarModule} from 'primeng/calendar';
import {OverlayPanelModule} from 'primeng/overlaypanel';
import {ListboxModule} from 'primeng/listbox';

import {AppComponent} from './app.component';
import {UsersComponent} from './users/users.component';
import {CommonService} from './common.service';
import {UserComponent} from './user/user.component';
import {UserRolesComponent} from './user-roles/user-roles.component';

import {DynamicFormModule} from './dynamic-form/dynamic-form.module';
import {DocumentComponent} from './document/document.component';

const appRoutes: Routes = [
  {path: 'users', component: UsersComponent},
  {path: 'user/:id', component: UserComponent},
  {path: 'document', component: DocumentComponent}
]


@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    UserComponent,
    UserRolesComponent,
    DocumentComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes, {enableTracing: true}),
    BrowserModule, ReactiveFormsModule, BrowserAnimationsModule,
    HttpClientModule,
    TableModule, ButtonModule, InputTextModule, DropdownModule, FormsModule, PanelModule, CalendarModule,
    OverlayPanelModule, ListboxModule,
    DynamicFormModule
  ],
  providers: [CommonService],
  bootstrap: [AppComponent]
})
export class AppModule { }

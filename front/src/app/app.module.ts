import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TableModule} from 'primeng/table';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';
import {PanelModule} from 'primeng/panel';
import {DropdownModule} from 'primeng/dropdown';
import {AppComponent} from './app.component';
import {UsersComponent} from './users/users.component';
import {CommonService} from './common.service';
import {UserComponent} from './user/user.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {OverlayPanelModule} from 'primeng/overlaypanel';
import {UserRolesComponent} from './user-roles/user-roles.component';

const appRoutes: Routes = [
  {path: 'users', component: UsersComponent},
  {path: 'user/:id', component: UserComponent}
]


@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    UserComponent,
    UserRolesComponent
  ],
  imports: [
    RouterModule.forRoot(appRoutes, {enableTracing: true}),
    BrowserModule, ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    TableModule,
    ButtonModule,
    InputTextModule,
    DropdownModule,
    FormsModule,
    PanelModule,
    OverlayPanelModule
  ],
  providers: [CommonService],
  bootstrap: [AppComponent]
})
export class AppModule { }

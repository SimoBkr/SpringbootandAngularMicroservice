import { AddAddressComponent } from './components/add-address/add-address.component';
import { ListAddressComponent } from './components/list-address/list-address.component';
import { NgModule, Component } from '@angular/core';
import { Routes ,RouterModule } from '@angular/router';
import { EditAddressComponent } from './components/edit-address/edit-address.component';
import { LoginComponent } from './components/login/login.component';
import { PageNotFoundComponent } from './components/partials/page-not-found/page-not-found.component';


const routes: Routes = [
  { path : "", redirectTo: '/address',pathMatch:'full'},
  { path : "address" , children:
  [
    { path: "",component: ListAddressComponent},
    { path : "create", component:AddAddressComponent},
    { path: "edit/:id",component: EditAddressComponent},
  ]
}, 
{ path : "login", component:LoginComponent},
{ path : "**", component:PageNotFoundComponent},

];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

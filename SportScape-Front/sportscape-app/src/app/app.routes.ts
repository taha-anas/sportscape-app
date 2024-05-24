import { Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {SignupComponent} from "./signup/signup.component";
import {SportFacilitiesComponent} from "./sport-facilities/sport-facilities.component";


export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {path: 'dashboard',
  component: DashboardComponent,children: [{ path: 'sport-facilities',
      component: SportFacilitiesComponent }
    ]
}
    ];

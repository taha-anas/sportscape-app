import { Component } from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient,HttpClientModule } from '@angular/common/http';
import {AuthRequest} from "../model/models";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,
  imports: [FormsModule, CommonModule, HttpClientModule, RouterLink]
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  error: string = '';
  authRequest : AuthRequest ={email: "",password: ""};

  constructor(private router: Router, private http: HttpClient) {}

  onSubmit() {
    if (this.email && this.password) {
      this.authenticateUser();
    } else {
      this.error = "Please enter both email and password.";
    }
  }

  private authenticateUser() {
    this.authRequest= { email: this.email, password: this.password };
    this.http.post<{token: string}>('http://localhost:8080/api/users/api/auth', this.authRequest)
      .subscribe({
        next: (response) => {
          console.log('Login successful', response);
          this.router.navigate(['/dashboard/sport-facilities']);
        },
        error: (error) => {
          console.error('Login failed', error);
          this.error = "Authentication failed. Check your credentials.";
        }
      });
  }
}

import { TokenService } from './../../services/token.service';
import { AuthService } from './../../services/auth.service';
import { logging } from 'protractor';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Console } from 'console';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    email: new FormControl(null,[Validators.required,Validators.email]),
    password: new FormControl(null,[Validators.required,Validators.minLength(9),Validators.minLength(9)]),
  })

  constructor(private authService : AuthService , private tokenSerice : TokenService , private router: Router) { }

  ngOnInit(): void {
  }

  login(){
    this.authService.login(this.loginForm.value).subscribe(res => this.handleResponse(res));
  }

  handleResponse(res) {
    this.tokenSerice.handle(res);
    this.router.navigateByUrl("/address");
  }

}

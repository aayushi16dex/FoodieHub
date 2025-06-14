import { Component, HostListener, inject } from '@angular/core';
import { UserService } from '../../service/user.service';
import { Dialog } from '@angular/cdk/dialog';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  userService = inject(UserService);
  dialog = inject(Dialog)
  toastrService = inject(ToastrService)

  loginForm: FormGroup
  signupForm: FormGroup
  constructor(private fb: FormBuilder) {
    this.loginForm = this.fb.group({
      email: '',
      password: ''
    })

     this.signupForm = this.fb.group({
      name:'',
      email: '',
      password: '',
    })
  }
  loginWithGoogle() {
    this.userService.googleSignIn()
  }

  closeLogin() {
    this.dialog.closeAll()
  }

  loginWithCredential() {
    this.userService.userLogin(this.loginForm.get('email').value, this.loginForm.get('password').value).then(resp => {
      this.toastrService.success('Login Successfully!')
      this.closeLogin()
    }).catch(err => {
      this.toastrService.error(
        ` ${err.message}!`,
      )

    })
  }

  signUp(){
    this.userService.register(this.signupForm.value)
  }
  signUpOrSignIn(type: number) {
    const container = document.getElementById("container");
    if (type === 1)
      container.classList.add("right-panel-active")
    else
      container.classList.remove("right-panel-active")
  }

}

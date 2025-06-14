import { Component, inject } from '@angular/core';
import { User } from '../../shared/models/User';
import { UserService } from '../../service/user.service';
import { Dialog } from '@angular/cdk/dialog';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent {
  user!: User;
  cartQuantity = 20;
  dialog = inject(Dialog);
  userData: any
  constructor(private userService: UserService,
  ) {
  }
  ngOnInit() {
    this.userService.userObservable.subscribe(resp => {
      if (resp) {
        this.userData = resp
      } else {
        this.userData = JSON.parse(localStorage.getItem('userData'))

      }
    })
  }
  logout() {
    this.userService.logout()
    localStorage.removeItem('userData')
  }
  login() {
    this.dialog.open(LoginComponent)
  }
}

import { Component } from '@angular/core';
import { User } from '../../shared/models/User';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  user!: User;
  cartQuantity = 20;
  constructor(private userService:UserService){}
  logout(){
    this.userService.logout()
  }
}

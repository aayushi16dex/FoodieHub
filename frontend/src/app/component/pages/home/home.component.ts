import { Component } from '@angular/core';
import { UserService } from '../../../service/user.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  constructor(private userService:UserService,
    private toastrService:ToastrService
  ){}

   async register() {
    try {
      await this.userService.registerWithDetails(
        {
          email: 'chitranjankr2412@gmail.com',
          name: "Chits",
          address: "Gurugram",
          isAdmin: false,
          isBlocked: false
        },
        "chits@2412"
      );
      
    } catch (err: any) {
          this.toastrService.error(err?.message, 'Register Failed')
    }
  }
}

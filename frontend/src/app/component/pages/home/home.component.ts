import { Component, ElementRef, ViewChild } from '@angular/core';
import { UserService } from '../../../service/user.service';
import { ToastrService } from 'ngx-toastr';
interface itemList  {
  name:string,
  description:string,
  price:number,
  image:string,
}
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})

export class HomeComponent {
  @ViewChild('carousel', { static: true }) carousel!: ElementRef<HTMLDivElement>;
  isAtStart = true;
  isAtEnd = false;
  popularItemList:itemList[]=  [ {
      name: 'Pappardelle',
      description: 'With Vegetable',
      price: 435.00,
      image: 'assets/products/food-3.jpg'
    },
    {
      name: 'Ravioli Stuffed',
      description: 'With Pesto Sauce',
      price: 735.00,
      image: 'assets/products/food-3.jpg'
    },
    {
      name: 'Pappardelle',
      description: 'With Vegetable',
      price: 335.00,
      image: 'assets/products/food-3.jpg'
    },
    {
      name: 'Ravioli Stuffed',
      description: 'With Pesto Sauce',
      price: 435.00,
      image: 'assets/products/food-3.jpg'
    }, {
      name: 'Ravioli Stuffed',
      description: 'With Pesto Sauce',
      price: 335.00,
      image: 'assets/products/food-3.jpg'
    },
    {
      name: 'Pappardelle',
      description: 'With Vegetable',
      price: 235.00,
      image: 'assets/products/food-3.jpg'
    },
    {
      name: 'Ravioli Stuffed',
      description: 'With Pesto Sauce',
      price: 935.00,
      image: 'assets/products/food-3.jpg'
    }, {
      name: 'Ravioli Stuffed',
      description: 'With Pesto Sauce',
      price: 635.00,
      image: 'assets/products/food-3.jpg'
    },
    {
      name: 'Pappardelle',
      description: 'With Vegetable',
      price: 765.00,
      image: 'assets/products/food-3.jpg'
    },
    {
      name: 'Ravioli Stuffed',
      description: 'With Pesto Sauce',
      price: 375.00,
      image: 'assets/products/food-3.jpg'
    }
  
  
  ]
   menuItems = [
    {
      name: "Signature Pasta",
      description: "Handmade pasta with truffle cream sauce and wild mushrooms",
      price: "$24",
      image: "assets/food-3.jpg",
    },
    {
      name: "Grilled Sea Bass",
      description: "Fresh sea bass with lemon herb butter and seasonal vegetables",
      price: "$32",
      image: "assets/food-3.jpg",
    },
    {
      name: "Braised Short Ribs",
      description: "Slow-cooked short ribs with red wine reduction and creamy polenta",
      price: "$28",
      image: "assets/food-3.jpg",
    },
    {
      name: "Truffle Risotto",
      description: "Creamy arborio rice with black truffle and aged parmesan",
      price: "$26",
      image: "assets/food-3.jpg",
    },
    
  ]
   testimonials = [
    {
      name: "Sarah Johnson",
      comment: "The best dining experience I've had in years. The atmosphere and food were both exceptional.",
      rating: 5,
    },
    {
      name: "Michael Chen",
      comment: "Incredible flavors and impeccable service. Will definitely be returning soon!",
      rating: 5,
    },
    {
      name: "Emma Rodriguez",
      comment: "A hidden gem with the most amazing pasta I've ever tasted. Highly recommended!",
      rating: 5,
    },
  ]

  constructor(private userService: UserService,
    private toastrService: ToastrService){}

  async register() {
    //   try {
    //     await this.userService.registerWithDetails(
    //       {
    //         email: 'chitranjankr2412@gmail.com',
    //         name: "Chits",
    //         address: "Gurugram",
    //         isAdmin: false,
    //         isBlocked: false
    //       },
    //       "chits@2412"
    //     );

    //   } catch (err: any) {
    //         this.toastrService.error(err?.message, 'Register Failed')
    //   }
  }


  private scrollInterval: any;

  ngAfterViewInit(): void {
    this.scrollInterval = setInterval(() => this.scrollCarousel('right'), 4000);
    this.checkScrollPosition();
  }

  ngOnDestroy(): void {
    clearInterval(this.scrollInterval);
  }

  scrollCarousel(direction: 'left' | 'right'): void {
    const container = this.carousel.nativeElement;
    const scrollAmount = 300;
    container.scrollBy({ left: direction === 'right' ? scrollAmount : -scrollAmount, behavior: 'smooth' });
    this.checkScrollPosition();
  }
  checkScrollPosition(): void {
    const el = this.carousel.nativeElement;
    this.isAtStart = el.scrollLeft <= 0;
    this.isAtEnd = el.scrollLeft + el.offsetWidth >= el.scrollWidth - 5;
  }

    reservationData = {
    name: "",
    email: "",
    date: "",
    time: "",
    guests: 2,
  }

  submitReservation() {
    console.log("Reservation submitted:", this.reservationData)
    alert("Thank you for your reservation! We will confirm shortly.")
    this.reservationData = {
      name: "",
      email: "",
      date: "",
      time: "",
      guests: 2,
    }
  }
}

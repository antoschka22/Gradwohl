import { Component } from '@angular/core';
import { AuthService } from 'src/app/service/auth/auth.service';
import { WarenbestellungService } from 'src/app/service/warenbestellung/warenbestellung.service';
import { warenbestellung } from 'src/model/warenbestellung/warenbestellung';

@Component({
  selector: 'app-login-first-page',
  templateUrl: './login-first-page.component.html',
  styleUrls: ['./login-first-page.component.scss']
})

export class LoginFirstPageComponent {

  isKundenbestellungDropdown: boolean = false;
  dropdownUp: boolean = true;

  currentDate = new Date();
  selectedDate: Date | null = null;

  warenbestellungen: warenbestellung[] = [];

  constructor(private warenbestellungService: WarenbestellungService,
              private authService: AuthService) { }

  ngOnInit(): void {
    this.getWarenbestellungen();
  }

  getWarenbestellungen() {
    //console.log(this.authService.getUsernameFromToken())

  this.warenbestellungService.getWarenbestellungByFiliale(14).subscribe((data: any) => {
    console.log(data)
  });
  }


  onDateSelect(date: Date | null): void {
    if (date) {
      this.selectedDate = date;
    }
  }
  toggleKundenbestellungDropdown() {
    this.isKundenbestellungDropdown = !this.isKundenbestellungDropdown;
    this.dropdownUp = !this.dropdownUp;
  }


}

import { Component } from '@angular/core';
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

  warenbestellungen: warenbestellung | undefined;

  constructor(private warenbestellungService: WarenbestellungService) { }

  ngOnInit(): void {
    this.getWarenbestellungen();
  }

  getWarenbestellungen() {
  this.warenbestellungService.getAllWarenbestellung().subscribe((data: any) => {
    this.warenbestellungen = data;
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

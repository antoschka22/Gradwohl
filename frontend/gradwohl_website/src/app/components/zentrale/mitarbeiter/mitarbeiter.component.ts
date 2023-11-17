import { Component } from '@angular/core';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';
import { mitabeiter } from 'src/model/mitarbeiter/mitarbeiter';

@Component({
  selector: 'app-mitarbeiter',
  templateUrl: './mitarbeiter.component.html',
  styleUrls: ['./mitarbeiter.component.scss']
})
export class MitarbeiterComponent {

  mitarbeiter: mitabeiter[] = [];

  constructor(private mitarbeiterService: MitabeiterService){}

  ngOnInit(): void {
    this.getMitarbeiter()
  }

  getMitarbeiter(){
    this.mitarbeiterService.getAllMitarbeiter().subscribe((mitarbeiter: any) => {
      this.mitarbeiter = mitarbeiter;
      mitarbeiter.forEach((mit: mitabeiter) => {
        if(mit.springer){
          mit.role.role = "Springer"
        }
      });
    })
  }

  changeMitarbeiter: mitabeiter | undefined;
  loeschen(mit: mitabeiter){
    this.changeMitarbeiter = mit;
  }

  searchTerm: string = '';
  get filteredMitarbeiter(): mitabeiter[] {
    return this.mitarbeiter.filter(employee =>
      employee.name.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      (employee.filiale && employee.filiale.name.toLowerCase().includes(this.searchTerm.toLowerCase())) ||
      (employee.filiale && employee.filiale.firma.name.toLowerCase().includes(this.searchTerm.toLowerCase())) ||
      employee.role.role.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}

import { Component } from '@angular/core';
import { FilialeService } from 'src/app/service/filiale/filiale.service';
import { MitabeiterService } from 'src/app/service/mitarbeiter/mitabeiter.service';
import { RoleService } from 'src/app/service/role/role.service';
import { filiale } from 'src/model/filiale/filiale';
import { mitabeiter } from 'src/model/mitarbeiter/mitarbeiter';
import { role } from 'src/model/role/role';

@Component({
  selector: 'app-mitarbeiter',
  templateUrl: './mitarbeiter.component.html',
  styleUrls: ['./mitarbeiter.component.scss']
})
export class MitarbeiterComponent {

  mitarbeiter: mitabeiter[] = [];
  filialen: filiale[] = [];
  rolen: role[] = [];

  constructor(private mitarbeiterService: MitabeiterService,
              private filialeService: FilialeService,
              private roleService: RoleService){}

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

    this.roleService.getRolen().subscribe((rolen: any) => {
      this.rolen = rolen;
    })

    this.filialeService.getAllFilialen().subscribe((filiale: any) => {
      this.filialen = filiale
    })
  }

  changeMitarbeiter: mitabeiter | undefined;
  loeschen(mit: mitabeiter){
    this.changeMitarbeiter = mit;
  }

  update(mit: mitabeiter){
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

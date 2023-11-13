import { Component } from '@angular/core';
import { KundenbestellungService } from 'src/app/service/kundenbestellung/kundenbestellung.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-kundenbestellung-speichern',
  templateUrl: './kundenbestellung-speichern.component.html',
  styleUrls: ['./kundenbestellung-speichern.component.scss']
})


export class KundenbestellungSpeichernComponent {

  auftragsname: string | null = null;
  datum: string | null = null;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {

  }
}
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Globals } from 'src/global/global';
import { mitabeiter } from 'src/model/mitarbeiter/mitarbeiter';

@Injectable({
  providedIn: 'root'
})
export class MitabeiterService {

  private baseUri: string = this.global.backendUri;

  constructor(private http: HttpClient,
              private global: Globals) { }

  
  getAllMitarbeiter(){
    return this.http.get(this.baseUri + '/mitarbeiter')
  }

  getMitarbeiterById(id: number){
    return this.http.get(this.baseUri + '/mitarbeiter/'+id)
  }

  deleteMitarbeiterById(id: number){
    return this.http.delete(this.baseUri + '/mitarbeiter/'+id)
  }

  updateMitarbeiterById(id: number, mitarbeiterToUpdate: mitabeiter){
    return this.http.put(this.baseUri + '/mitarbeiter/'+id, mitarbeiterToUpdate)
  }

  insertMitarbeiter(mitabeiterToInsert: mitabeiter){
    return this.http.post(this.baseUri + '/mitarbeiter/', mitabeiterToInsert)
  }

}

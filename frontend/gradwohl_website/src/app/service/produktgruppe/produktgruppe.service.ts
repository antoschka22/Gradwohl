import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Globals } from 'src/global/global';
import { produktgruppe } from 'src/model/produktgruppe/produktgruppe';

@Injectable({
  providedIn: 'root'
})
export class ProduktgruppeService {

  private baseUri: string = this.global.backendUri;

  constructor(private http: HttpClient,
              private global: Globals) { }

  getProduktgruppen(){
    return this.http.get(this.baseUri + '/produktgruppe')
  }

  insertProduktgruppe(produktgruppeToInsert: produktgruppe){
    return this.http.post(this.baseUri+'/produktgruppe', produktgruppeToInsert)
  }

  updateProduktgruppe(id: string, produktgruppeToInsert: produktgruppe){
    return this.http.put(this.baseUri+'/produktgruppe'+id, produktgruppeToInsert)
  }

  deleteProdukgruppe(id: string){
    return this.http.delete(this.baseUri+'/'+id)
  }
}
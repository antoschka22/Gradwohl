import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Globals } from 'src/global/global';
import { produkt } from 'src/model/produkt/produkt';

@Injectable({
  providedIn: 'root'
})
export class ProduktService {

  private baseUri: string = this.global.backendUri;

  constructor(private http: HttpClient,
              private global: Globals) { }

  getAllProdukts(){
    return this.http.get(this.baseUri + '/produkt')
  }

  getProduktByProduktgruppe(produktgruppe: string){
    return this.http.get(this.baseUri+'/produkt/'+produktgruppe)
  }

  getProduktByProduktgruppeAndHB(produktgruppe: string, hb: boolean){
    return this.http.get(this.baseUri+'/produkt/'+produktgruppe+'/'+hb)
  }

  getProduktById(id: number){
    return this.http.get(this.baseUri+'/produkt/ware/'+id)
  }

  getProduktByHB(hb: boolean){
    return this.http.get(this.baseUri+'/produkt/hb/'+hb)
  }

  insertProdukt(produktToInsert: produkt){
    return this.http.post(this.baseUri+'/produkt', produktToInsert)
  }

  deleteProdukt(id: number){
    return this.http.delete(this.baseUri+'/produkt/'+id)
  }

  updateProdukt(id: number, produktToInsert: produkt){
    return this.http.put(this.baseUri+'/produkt/'+id, produktToInsert)
  }
}

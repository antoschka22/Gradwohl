import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Globals } from 'src/global/global';
import { filiale } from 'src/model/filiale/filiale';

@Injectable({
  providedIn: 'root'
})
export class FilialeService {

  private baseUri: string = this.global.backendUri;

  constructor(private http: HttpClient,
              private global: Globals) { }

  
  getAllFilialen(){
    return this.http.get(this.baseUri + '/filiale')
  }

  getFilialeById(id: number){
    return this.http.get(this.baseUri+'/filiale/'+id)
  }

  insertFiliale(filialeToInsert: filiale){
    return this.http.post(this.baseUri+'/filiale', filialeToInsert)
  }

  deleteFiliale(id: number){
    return this.http.delete(this.baseUri+'/filiiale/'+id)
  }

  updateFiliale(id: number, filialeToInsert: any){
    return this.http.put(this.baseUri+'/filiale/'+id, filialeToInsert)
  }
}

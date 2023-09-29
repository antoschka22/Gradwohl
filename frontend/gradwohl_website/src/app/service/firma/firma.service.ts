import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Globals } from 'src/global/global';
import { firma } from 'src/model/firma/firma';

@Injectable({
  providedIn: 'root'
})
export class FirmaService {

  private baseUri: string = this.global.backendUri;

  constructor(private http: HttpClient,
              private global: Globals) { }

  
  getAllFirma(){
    return this.http.get(this.baseUri + '/firma')
  }

  insertFirma(firmaToInsert: firma){
    return this.http.post(this.baseUri+'/firma', firmaToInsert)
  }

  updateFirma(id: string, firmaToInsert: firma){
    return this.http.put(this.baseUri+'/firma/'+id, firmaToInsert)
  }

  deleteFirma(id: string){
    return this.http.delete(this.baseUri+'/firma/'+id)
  }
}

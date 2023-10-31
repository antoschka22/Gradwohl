import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Globals } from 'src/global/global';
import { urlaubstage } from 'src/model/urlaubstage/urlaubstage';

@Injectable({
  providedIn: 'root'
})
export class UrlaubstageService {

  private baseUri: string = this.global.backendUri;

  constructor(private http: HttpClient,
              private global: Globals) { }

  getAllUrlaubstage(){
    return this.http.get(this.baseUri + '/urlaubstage')
  }

  getUrlaubstageById(id: number){
    return this.http.get(this.baseUri + '/urlaubstage/'+id)
  }

  postUrlaubstag(urlaubstage: urlaubstage[]){
    return this.http.post(this.baseUri + '/urlaubstage', urlaubstage)
  }

  updateUrlaubstag(id: number, urlaubstage: urlaubstage){
    return this.http.put(this.baseUri + '/urlaubstage/'+id, urlaubstage)
  }

  deleteUrlaubstag(id: number){
    return this.http.delete(this.baseUri+'/urlaubstage/'+id)
  }
}

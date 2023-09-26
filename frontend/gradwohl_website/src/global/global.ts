import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root',
})
export class Globals{
    readonly backendUri: string = this.findBackendUrl();

    private findBackendUrl(): string{
        if(window.location.port === '4200'){
            // local 'ng serve'
            return window.location.protocol + '//' + window.location.hostname + ':8080';
        }else if(window.location.port === '8080'){
            // Docker deployed
            return window.location.protocol + '//' +  window.location.hostname + ':5050'
        }else{
            // assume deployed somewhere and backend is available at same host/port as frontend
            return(
                'https://' +
                window.location.hostname
            );
        }
    }
}
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Cake } from "./cake";
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CakeManagerService {
  
  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getCakes(): Observable<Cake[]> {

    return this.http.get<Cake[]>(`${this.apiServiceUrl}/cakes/all`)
  }
  
  addCake(body: Cake): Observable<Cake> {
    
    return this.http.post<Cake>(`${this.apiServiceUrl}/cakes/add`, body)
  }
}
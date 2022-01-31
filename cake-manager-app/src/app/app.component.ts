import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Cake } from './cake';
import { CakeManagerService } from './cake-manager.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
  public cakes: Cake[] = [];

  constructor(private cakeManagerService : CakeManagerService) {}
  
  ngOnInit(): void {
    this.getCakes();
  }

  public getCakes(): void {

    this.cakeManagerService.getCakes().subscribe(
      (response: Cake[]) => {
        this.cakes = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  public onAddCake(addForm: NgForm): void {
    document.getElementById('add-cake-form')?.click();
    this.cakeManagerService.addCake(addForm.value).subscribe(
      (response: Cake) => {
        console.log(response);
        this.getCakes();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }
}

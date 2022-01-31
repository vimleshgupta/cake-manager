import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Cake } from '../cake';
import { CakeManagerService } from '../cake-manager.service';


@Component({
  selector: 'app-cakes',
  template: `
    <div class="collapse navbar-collapse" id="navbarColor02">
     <ul class="navbar-nav mr-auto">
     <li class="nav-item active">
           <a class="nav-link" (click)="onOpenModel()">Add Cake <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item active">
           <a class="nav-link" (click)="download()">Download cakes<span class="sr-only">(current)</span></a>
        </li>
        
     </ul>
  </div>
  `,
  styles: [
  ]
})
export class CakesComponent implements OnInit {

  
  constructor(private cakeManagerService : CakeManagerService) {}

  ngOnInit(): void {
    
  }

  public onOpenModel(): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    button.setAttribute('data-target', '#addCakeModal');
    container?.appendChild(button);
    button.click();

  }
  public download(): void {

    var downloader = document.createElement('a');

    downloader.setAttribute('download', 'cakes.json');
    this.cakeManagerService.getCakes().subscribe(
      (response: Cake[]) => {
        const data = "data:text/json;charset=utf-8," + encodeURIComponent(JSON.stringify(response));
        downloader.setAttribute('href', data);
        downloader.click();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}

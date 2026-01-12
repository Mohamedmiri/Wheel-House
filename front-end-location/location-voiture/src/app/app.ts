import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ClientComponent } from './client/client.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ClientComponent],   // <= IMPORTANT
  templateUrl: './app.html'
})
export class App {}

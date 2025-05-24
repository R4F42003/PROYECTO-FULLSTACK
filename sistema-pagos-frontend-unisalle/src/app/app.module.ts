import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

// Angular Material
import { MatToolbarModule } from '@angular/material/toolbar';

// Paginación
import { NgxPaginationModule } from 'ngx-pagination';

// Componentes personalizados
import { PagoCrearComponent } from './componentes/pago-crear/pago-crear.component';
import { PagoStatusComponent } from './componentes/pago-status/pago-status.component';
import { PagoArchivoComponent } from './componentes/pago-archivo/pago-archivo.component';
import { PagoDashboardComponent } from './componentes/pago-dashboard/pago-dashboard.component';
import { ListaEstudiantesComponent } from './componentes/lista-estudiantes/lista-estudiantes.component';
import { ListaPagosComponent } from './componentes/lista-pagos/lista-pagos.component';
import { AdminComponent } from './admin/admin.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    PagoCrearComponent,
    PagoStatusComponent,
    PagoArchivoComponent,
    PagoDashboardComponent,
    ListaEstudiantesComponent,
    ListaPagosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatToolbarModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

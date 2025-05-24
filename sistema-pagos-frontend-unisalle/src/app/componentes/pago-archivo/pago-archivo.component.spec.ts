import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PagoArchivoComponent } from './pago-archivo.component';

describe('PagoArchivoComponent', () => {
  let component: PagoArchivoComponent;
  let fixture: ComponentFixture<PagoArchivoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PagoArchivoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PagoArchivoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PagoCrearComponent } from './pago-crear.component';

describe('PagoCrearComponent', () => {
  let component: PagoCrearComponent;
  let fixture: ComponentFixture<PagoCrearComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PagoCrearComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PagoCrearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

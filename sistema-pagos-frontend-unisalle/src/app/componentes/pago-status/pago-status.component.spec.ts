import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PagoStatusComponent } from './pago-status.component';

describe('PagoStatusComponent', () => {
  let component: PagoStatusComponent;
  let fixture: ComponentFixture<PagoStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PagoStatusComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PagoStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

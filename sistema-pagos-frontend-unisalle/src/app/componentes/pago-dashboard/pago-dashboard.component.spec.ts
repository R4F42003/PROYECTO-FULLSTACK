import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PagoDashboardComponent } from './pago-dashboard.component';

describe('PagoDashboardComponent', () => {
  let component: PagoDashboardComponent;
  let fixture: ComponentFixture<PagoDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PagoDashboardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PagoDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

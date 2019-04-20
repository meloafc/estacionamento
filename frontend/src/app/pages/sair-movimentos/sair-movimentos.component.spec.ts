import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SairMovimentosComponent } from './sair-movimentos.component';

describe('SairMovimentosComponent', () => {
  let component: SairMovimentosComponent;
  let fixture: ComponentFixture<SairMovimentosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SairMovimentosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SairMovimentosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

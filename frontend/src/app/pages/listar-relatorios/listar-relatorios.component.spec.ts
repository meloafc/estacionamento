import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarRelatoriosComponent } from './listar-relatorios.component';

describe('ListarRelatoriosComponent', () => {
  let component: ListarRelatoriosComponent;
  let fixture: ComponentFixture<ListarRelatoriosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListarRelatoriosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarRelatoriosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

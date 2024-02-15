import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {ActivitiesService} from "../../component-funcionality/services/activities/activities.service";
import {HotToastService} from "@ngneat/hot-toast";

@Component({
  selector: 'app-activities-form',
  templateUrl: './activities-form.component.html',
  styleUrls: ['./activities-form.component.scss']
})
export class ActivitiesFormComponent implements OnInit, OnDestroy {

  funcionaryDataForm: FormGroup = new FormGroup({});
  ubigeoData: any[] = [];

  constructor(public _funcionaryService: ActivitiesService,
              private _router: Router,
              private toastService: HotToastService,
              private _fb: FormBuilder) {
    this.funcionaryDataForm = this._fb.group({
      id: [null],
      name: ['', Validators.required],
      description: ['', Validators.required],
      duration: ['', Validators.required],
      date: ['2023-12-20'],
      location: ['', Validators.required],
      type_soa: ['', Validators.required],
      type_pronacej: ['', Validators.required],
      active: ['A'],
    });
    if (this._funcionaryService.funcionarySelected) {
      this.funcionaryDataForm.patchValue(this._funcionaryService.funcionarySelected);
    }
  }

  ngOnInit(): void {

  }

  navigateToList() {
    this._router.navigate(['activities']).then(() => {
      console.log('Funcionary List.')
    })
  }



  saveOrUpdateFuncionary() {
    if (this._funcionaryService.funcionarySelected) {
      // Actualizar || Modificar
      this.updateDataFuncionary();
    } else {
      // Registrar || Crear
      this.registerNewDataFuncionary();
    }
  }

  registerNewDataFuncionary() {
    console.log('Datos ingresados en el formulario: ', this.funcionaryDataForm.value);
    this._funcionaryService.saveNewFuncionary(this.funcionaryDataForm.value).subscribe((dataNewFuncionary: any) => {
      //console.log('New Data Funcionary: ', dataNewFuncionary)
      this.toastService.success('Registrado exitosamente!');
      this.navigateToList();
    });
  }

  updateDataFuncionary() {
    console.log('Datos ingresados en el formulario: ', this.funcionaryDataForm.value);
    this._funcionaryService.updateDataFuncionary(this.funcionaryDataForm.value).subscribe((dataUpdateFuncionary: any) => {
      //console.log('Update Data Funcionary: ', dataUpdateFuncionary)
      this.toastService.success('Modificado exitosamente!');
      this.funcionaryDataForm.reset();
      this.navigateToList();
    });
  }

  ngOnDestroy(): void {
    this._funcionaryService.funcionarySelected = undefined;
  }
}

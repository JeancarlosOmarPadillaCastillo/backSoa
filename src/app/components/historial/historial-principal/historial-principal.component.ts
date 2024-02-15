import {Component, OnInit, ViewChild} from '@angular/core';
import {HistorialService} from "../../component-funcionality/services/historial/historial.service";
import {TeenService} from "../../component-funcionality/services/teen/teen.service";
import {FuncionaryService} from "../../component-funcionality/services/funcionary/funcionary.service";
import {ActivitiesService} from "../../component-funcionality/services/activities/activities.service";
import { MatPaginator } from '@angular/material/paginator';
import {FormControl} from "@angular/forms";
import {Router} from "@angular/router";

import { MatTableDataSource } from '@angular/material/table';
import {AttendanceService} from "../../component-funcionality/services/attendance/attendance.service";
@Component({
  selector: 'app-historial-principal',
  templateUrl: './historial-principal.component.html',
  styleUrls: ['./historial-principal.component.scss']
})
export class HistorialPrincipalComponent implements OnInit {

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatPaginator) paginatorInactive!: MatPaginator;

  attendanceData: any[] = [];
  activitiesData: any[] = [];
  programaData: any[] = [];
  funcionaryData: any[] = [];
  teenagerData: any[] = [];
  programDataActive: any[] = [];
  programDataInactive: any[] = [];
  selectedValue: string[] = [];
  showDataActive = false;
  showDataInactive = false;
  selectedprogram: any;
  showDetails = false;
  displayedColumns: string[] = [
    'description2',
    'name',
    'beneficiary',
    'responsible',
    'description',
    'duration'
  ];
  filterControl = new FormControl('');
  dataSourceActive = new MatTableDataSource(this.programDataActive);
  dataSourceInactive = new MatTableDataSource(this.programDataInactive);
  filterControlCombined = new FormControl('');

  constructor(public _operationalunitService: HistorialService,
              private _router: Router) {
  }

  ngOnInit(): void {
    console.log('ngOnInit called');
    this.findAllDataCompleteOperationalUnit();
    this.findAllDataActiveOperationalUnit();
    this.findAllDataAttendance();
    this.findAllDataActivities();
    this.findAllDataPrograma();
    this.findAllDataFuncionary();
    this.findAllDataTeenager();

    if (this.filterControl) {
      this.filterControl.valueChanges.subscribe(() => {
        console.log('Filter changed');
        this.applyFilter();
      });
    }
  }
  filterTeenagersByAttendance(id_attendance: number): any[] {
    // Find the program object in the programData array where id_program matches
    const program = this.attendanceData.find((item) => item.id_attendance === id_attendance);

    // Check if the program is found
    if (program) {
      // Convert id_activities to number
      const idActivities: number = parseInt(`${program.id_teenager}`, 10);

      // Filter the teenagerData array based on the id
      const filteredTeenagers = this.teenagerData.filter((item) => item.id === idActivities);

      return filteredTeenagers;
    } else {
      // Return an empty array if the program is not found
      return [];
    }
  }

  applyFilter() {
    const filterValue = (this.filterControl?.value || '').toLowerCase().trim();
    console.log('Filter changed:', filterValue);

    // Verifica si los datos están disponibles
    console.log('Program Data Active:', this.programDataActive);

    // Filtrar los datos manualmente
    const filteredData = this.programDataActive.filter((data) => {
      // Filtrar los adolescentes basados en la asistencia
      const filteredTeenagers = this.filterTeenagersByAttendance(data.id_attendance);

      // Retorna false si no hay datos de adolescente para filtrar
      if (filteredTeenagers.length === 0) {
        return false;
      }

      const teenager = filteredTeenagers[0]; // Suponiendo que solo hay una coincidencia
      const searchTerms = [
        teenager.name,
        teenager.father_last_name,
        teenager.mother_last_name,
        teenager.document_number
      ].map(term => (term || '').toLowerCase());

      console.log('Checking', searchTerms, '- Filter:', filterValue);

      // Retorna true si alguna de las condiciones coincide con el filtro
      return searchTerms.some(term => term.includes(filterValue));
    });

    console.log('Filtered Data:', filteredData);

    // Asignar los datos filtrados a dataSourceActive
    this.dataSourceActive.data = filteredData;
  }






  customFilterPredicate(data: any, filter: string): boolean {
    const searchTerms = [
      data.teenResponseDto?.name,
      data.teenResponseDto?.father_last_name,
      data.teenResponseDto?.mother_last_name,
      `${data.teenResponseDto?.father_last_name} ${data.teenResponseDto?.mother_last_name}, ${data.teenResponseDto?.name}`,
      data.teenResponseDto?.document_number
    ].filter(term => term !== undefined).map(term => term.toLowerCase());

    // Retorna true si alguna de las condiciones coincide con el filtro
    return searchTerms.some(term => term.includes(filter));
  }





  findAllDataCompleteOperationalUnit() {
    this._operationalunitService.findAll().subscribe((DataFuncionaryBD: any) => {
      console.log('Data Teen:', DataFuncionaryBD);

      // Asignar los datos a filteredOptions
    });
  }

  findAllDataActiveOperationalUnit() {
    this._operationalunitService
      .findAllDataActive()
      .subscribe((DataFuncionaryBDActive: any) => {
        this.programDataActive = DataFuncionaryBDActive;
        this.dataSourceActive = new MatTableDataSource(this.programDataActive);
        this.dataSourceActive.paginator = this.paginator;
      });
  }

  findAllDataAttendance() {
    this._operationalunitService.findAllAttendance().subscribe((dataUbigeo: any) => {
      console.log('Attendance: ', dataUbigeo); // Running successfully
      this.attendanceData = dataUbigeo;
    });
  }

  findAllDataActivities() {
    this._operationalunitService.findAllActivities().subscribe((dataUbigeo: any) => {
      console.log('actividades : ', dataUbigeo); // Running successfully
      this.activitiesData = dataUbigeo;
    });
  }

  findAllDataPrograma() {
    this._operationalunitService.findAllPrograms().subscribe((dataUbigeo: any) => {
      console.log('programa : ', dataUbigeo); // Running successfully
      this.programaData = dataUbigeo;
    });
  }

  findAllDataFuncionary() {
    this._operationalunitService.findAllFuncionary().subscribe((dataUbigeo: any) => {
      console.log('funcionarios : ', dataUbigeo); // Running successfully
      this.funcionaryData = dataUbigeo;
    });
  }

  findAllDataTeenager() {
    this._operationalunitService.findAllTennager().subscribe((dataUbigeo: any) => {
      console.log('Adolescentes : ', dataUbigeo); // Running successfully
      this.teenagerData = dataUbigeo;
    });
  }

  showFuncionaryDetails(operativeunit: any) {
    this.selectedprogram = operativeunit;
    this.showDetails = true;
  }

  closeDetails() {
    this.selectedprogram = null;
    this.showDetails = false;
  }

  showActive() {
    this.showDataActive = true;
    this.hideInactive();
    this.findAllDataCompleteOperationalUnit();
  }

  hideActive() {
    this.showDataActive = false;
  }

  showInactive() {
    this.showDataInactive = true;
    this.hideActive();
  }

  hideInactive() {
    this.showDataInactive = false;
  }

  getDataCompleteProgramById(id_attendance: number) {
    // Find the program object in the programData array where id_program matches
    const Historial = this.attendanceData.find((item) => item.id_attendance === id_attendance);
    console.log('Prueba: ',this.attendanceData);
    // Check if the program is found
    if (Historial) {
      // Concatenate relevant information and return the result
      return `${Historial.attendance}`;
    } else {
      // Return a message if the program is not found
      return 'Asistencia no encontrado.'; // Program not found.
    }
  }

  getDataCompleteProgramByDate(id_attendance: number) {
    // Find the program object in the programData array where id_program matches
    const Historial = this.attendanceData.find((item) => item.id_attendance === id_attendance);

    // Check if the program is found
    if (Historial) {
      // Concatenate relevant information and return the result
      return `${Historial.date}`;
    } else {
      // Return a message if the program is not found
      return 'fecha no encontrado.'; // Program not found.
    }
  }

  getDataCompleteProgramByActivities(id_attendance: number) {
    // Find the program object in the attendanceData array where id_attendance matches
    const Historial = this.attendanceData.find((item) => item.id_attendance === id_attendance);

    // Check if the program is found
    if (Historial) {
      // Convert id_activities to number
      const idActivities: number = parseInt(`${Historial.id_activities}`, 10);

      // Find the activity object in the activitiesData array where id matches
      const activity = this.activitiesData.find((item) => item.id === idActivities);

      // Check if the activity is found
      if (activity) {
        // Return the name of the activity
        return `${activity.name}`;
      } else {
        // Return a message if the activity is not found
        return 'Actividad no encontrada.';
      }
    } else {
      // Return a message if the program is not found
      return 'Programa no encontrado.';
    }
  }


  getDataCompleteProgramByPrograma(id_attendance: number) {
    // Find the program object in the attendanceData array where id_attendance matches
    const Historial = this.attendanceData.find((item) => item.id_attendance === id_attendance);

    // Check if the program is found
    if (Historial) {
      // Convert id_activities to number
      const idActivities: number = parseInt(`${Historial.id_programs}`, 10);

      // Find the activity object in the activitiesData array where id matches
      const activity = this.programaData.find((item) => item.id_program === idActivities);

      // Check if the activity is found
      if (activity) {
        // Return the name of the activity
        return `${activity.name}`;
      } else {
        // Return a message if the activity is not found
        return 'Actividad no encontrada.';
      }
    } else {
      // Return a message if the program is not found
      return 'Programa no encontrado.';
    }
  }



  getDataCompleteProgramByFuncionary(id_attendance: number) {
    // Find the program object in the programData array where id_program matches
    const Historial = this.attendanceData.find((item) => item.id_attendance === id_attendance);

    // Check if the program is found
    if (Historial) {
      // Convert id_activities to number
      const idActivities: number = parseInt(`${Historial.id_funcionary}`, 10);

      // Find the activity object in the activitiesData array where id matches
      const activity = this.funcionaryData.find((item) => item.id_funcionary === idActivities);

      // Check if the activity is found
      if (activity) {
        // Return the name of the activity
        return `${activity.name}`;
      } else {
        // Return a message if the activity is not found
        return 'Programa no encontrada.';
      }
    } else {
      // Return a message if the program is not found
      return 'Programa no encontrado.';
    }
  }
  getDataCompleteProgramByTennager(id_attendance: number) {
    const filteredTeenagers = this.filterTeenagersByAttendance(id_attendance);

    if (filteredTeenagers.length > 0) {
      // If there are filtered teenagers, return the formatted string
      const teenager = filteredTeenagers[0]; // Assuming there's only one match
      return `${teenager.father_last_name} ${teenager.mother_last_name}, ${teenager.name}`;
    } else {
      return 'Adolescente no encontrado.';
    }
  }


}

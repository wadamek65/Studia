def read(file_name):
    job_list = []
    with open(file_name) as file:
        file.readline()
        for line in file:
            job_list.append(list(map(int, line.split())))
        return sorted(job_list, key=sum, reverse=True)


# funkcja znajdujaca Cmax dla danej permutacji zadan
def find_cmax(job_list):
    Cmax = []
    tasks_amount = len(job_list[0])
    # Cmax dla kazdej maszyny osobno
    for i in range(0, tasks_amount):
        Cmax.append(0)
    for i in range(0, len(job_list)):
        for t in range(0, tasks_amount):
            # przejdz po wszystkich zadaniach kazdego zestawu
            if t == 0:
                Cmax[t] += job_list[i][t] # wynik Cmax dla pierwszej maszyny zawsze bedzie bez zmian
            else:
                Cmax[t] = max(Cmax[t-1], Cmax[t]) + job_list[i][t] # sprawdza czy skonczylo sie zadanie na poprzedniej maszynie i wrzuca kolejne

    return Cmax[len(Cmax)-1]


# funkcja tworzy rozne permutacje kolejnosci zadan poprzez przestawianie ostatniego zadania w inne miejsca
def create_permutations(job_list):
    Cmax = 1000000000
    jobs_amount = len(job_list) # ilosc roznych zestawow zadan
    optimal_job_list = job_list # wstepna deklaracja
    for i in range(0, jobs_amount):
        new_job_list = job_list[:jobs_amount-1]
        new_job_list.insert(i, job_list[jobs_amount-1]) # ostatnie zadanie wstawia od poczatku miedzy inne zadania i liczy dla niego Cmax
        temp_Cmax = find_cmax(new_job_list)
        if temp_Cmax < Cmax:
            Cmax = temp_Cmax
            optimal_job_list = new_job_list # jesli Cmax jest mniejsze to to jest optymalne ustawienie zadan dla kolejnych permutacji
    return Cmax, optimal_job_list


def neh(file_name):
    job_list = read(file_name)
    Cmax = 0
    for i in range(2, len(job_list)+1):
        Cmax, new_job_list = create_permutations(job_list[:i])
        job_list[:i] = new_job_list # leci po kolei, najpierw zestawy 0, 1, pozniej 0, 1, 2 pozniej 0, 1, 2, 3 itd.
    print(Cmax)


for i in range(1, 10):
    neh('data/NEH'+str(i)+'.DAT')

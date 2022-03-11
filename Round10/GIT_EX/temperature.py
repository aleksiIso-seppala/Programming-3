
def main():

    transform_direction = input("Select transform direction (CF) or (FC): ")

    try:
        input_temp = float(input("Input temperature: "))
    except:
        print("Not valid input")
        exit()

    if transform_direction == "CF":
        print("{} C = {} F".format(input_temp, t_transform(input_temp, 'c')))
    if transform_direction == "FC":
        print("{} F = {} C".format(input_temp, t_transform(input_temp, 'f')))


def t_transform(temp, type):

    if type == 'c':
        return float(temp * 9/5 + 32)
    if type == 'f':
        return float(temp * 5/9 - 32*(5/9))
    return None


if __name__ == "__main__":
    main()
